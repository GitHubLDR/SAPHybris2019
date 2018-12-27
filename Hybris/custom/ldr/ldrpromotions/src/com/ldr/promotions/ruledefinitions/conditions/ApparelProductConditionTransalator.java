/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2018 SAP SE
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * Hybris ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with SAP Hybris.
 */
package com.ldr.promotions.ruledefinitions.conditions;





import de.hybris.platform.ruledefinitions.AmountOperator;
import de.hybris.platform.ruledefinitions.CollectionOperator;
import de.hybris.platform.ruledefinitions.conditions.builders.RuleIrAttributeConditionBuilder;
import de.hybris.platform.ruledefinitions.conditions.builders.RuleIrAttributeRelConditionBuilder;
import de.hybris.platform.ruleengineservices.compiler.RuleCompilerContext;
import de.hybris.platform.ruleengineservices.compiler.RuleConditionTranslator;
import de.hybris.platform.ruleengineservices.compiler.RuleIrAttributeCondition;
import de.hybris.platform.ruleengineservices.compiler.RuleIrAttributeOperator;
import de.hybris.platform.ruleengineservices.compiler.RuleIrAttributeRelCondition;
import de.hybris.platform.ruleengineservices.compiler.RuleIrCondition;
import de.hybris.platform.ruleengineservices.compiler.RuleIrExistsCondition;
import de.hybris.platform.ruleengineservices.compiler.RuleIrGroupCondition;
import de.hybris.platform.ruleengineservices.compiler.RuleIrGroupOperator;
import de.hybris.platform.ruleengineservices.compiler.RuleIrLocalVariablesContainer;
import de.hybris.platform.ruleengineservices.compiler.RuleIrNotCondition;
import de.hybris.platform.ruleengineservices.rao.CartRAO;
import de.hybris.platform.ruleengineservices.rao.OrderEntryRAO;
import de.hybris.platform.ruleengineservices.rao.ProductRAO;
import de.hybris.platform.ruleengineservices.rule.data.RuleConditionData;
import de.hybris.platform.ruleengineservices.rule.data.RuleConditionDefinitionData;
import de.hybris.platform.ruleengineservices.rule.data.RuleParameterData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 *
 */
public class ApparelProductConditionTransalator implements RuleConditionTranslator
{

	@Override
	public RuleIrCondition translate(final RuleCompilerContext context, final RuleConditionData condition,
			final RuleConditionDefinitionData arg2)
	{
		final String productRaoVariable = context.generateVariable(ProductRAO.class);
		final String orderEntryRaoVariable = context.generateVariable(OrderEntryRAO.class);
		final String cartRaoVariable = context.generateVariable(CartRAO.class);


		final Map conditionParameters = condition.getParameters();
		final RuleParameterData operatorParameter = (RuleParameterData) conditionParameters.get("operator");
		final RuleParameterData quantityParameter = (RuleParameterData) conditionParameters.get("quantity");
		final RuleParameterData productsOperatorParameter = (RuleParameterData) conditionParameters.get("products_operator");
		final RuleParameterData productsParameter = (RuleParameterData) conditionParameters.get("products");

		final AmountOperator operator = (AmountOperator) operatorParameter.getValue();
		final Integer quantity = (Integer) quantityParameter.getValue();
		final CollectionOperator productsOperator = (CollectionOperator) productsOperatorParameter.getValue();
		final List products = (List) productsParameter.getValue();

		final RuleIrGroupCondition apparelProductCondition = new RuleIrGroupCondition();
		apparelProductCondition.setOperator(RuleIrGroupOperator.AND);
		apparelProductCondition.setChildren(new ArrayList<>());



		final RuleIrAttributeCondition irProductCondition = new RuleIrAttributeCondition();
		irProductCondition.setVariable(productRaoVariable);
		irProductCondition.setAttribute("code");
		irProductCondition.setOperator(RuleIrAttributeOperator.IN);
		irProductCondition.setValue(products);

		final RuleIrAttributeRelCondition irOrderEntryProductRel = new RuleIrAttributeRelCondition();
		irOrderEntryProductRel.setVariable(orderEntryRaoVariable);
		irOrderEntryProductRel.setAttribute("product");
		irOrderEntryProductRel.setOperator(RuleIrAttributeOperator.EQUAL);
		irOrderEntryProductRel.setTargetVariable(productRaoVariable);

		final RuleIrAttributeCondition irOrderEntryQuantityCondition = new RuleIrAttributeCondition();
		irOrderEntryQuantityCondition.setVariable(orderEntryRaoVariable);
		irOrderEntryQuantityCondition.setAttribute("quantity");
		irOrderEntryQuantityCondition.setOperator(RuleIrAttributeOperator.valueOf(operator.name()));
		irOrderEntryQuantityCondition.setValue(quantity);

		final RuleIrAttributeRelCondition irCartOrderEntryRel = new RuleIrAttributeRelCondition();
		irCartOrderEntryRel.setVariable(cartRaoVariable);
		irCartOrderEntryRel.setAttribute("entries");
		irCartOrderEntryRel.setOperator(RuleIrAttributeOperator.CONTAINS);
		irCartOrderEntryRel.setTargetVariable(orderEntryRaoVariable);

		final ArrayList irConditions = new ArrayList();
		irConditions.add(irProductCondition);
		irConditions.add(irOrderEntryProductRel);
		irConditions.add(irOrderEntryQuantityCondition);
		irConditions.add(irCartOrderEntryRel);


		//apparelProductCondition.setChildren(irConditions);



		if (CollectionOperator.NOT_CONTAINS.equals(productsOperator))
		{
			final RuleIrNotCondition irNotProductCondition = new RuleIrNotCondition();
			irNotProductCondition.setChildren(irConditions);
			apparelProductCondition.getChildren().add(irNotProductCondition);
		}
		else
		{
			apparelProductCondition.getChildren().addAll(irConditions);
			if (CollectionOperator.CONTAINS_ALL.equals(productsOperator))
			{
				this.addContainsAllProductsConditions(context, operator, quantity, products, apparelProductCondition);
			}
		}






		return apparelProductCondition;
	}


	protected void addContainsAllProductsConditions(final RuleCompilerContext context, final AmountOperator operator,
			final Integer quantity, final List<String> products, final RuleIrGroupCondition irQualifyingProductsCondition)
	{
		final String cartRaoVariable = context.generateVariable(CartRAO.class);// 143
		final Iterator arg7 = products.iterator();// 145

		while (arg7.hasNext())
		{
			final String product = (String) arg7.next();
			final RuleIrLocalVariablesContainer variablesContainer = context.createLocalContainer();// 147
			final String containsProductRaoVariable = context.generateLocalVariable(variablesContainer, ProductRAO.class);// 148
			final String containsOrderEntryRaoVariable = context.generateLocalVariable(variablesContainer, OrderEntryRAO.class);// 149
			final RuleIrAttributeCondition irContainsProductCondition = RuleIrAttributeConditionBuilder
					.newAttributeConditionFor(containsProductRaoVariable).withAttribute("code")
					.withOperator(RuleIrAttributeOperator.EQUAL).withValue(product).build();// 151 152 153 154 155
			final RuleIrAttributeRelCondition irContainsOrderEntryProductRel = RuleIrAttributeRelConditionBuilder
					.newAttributeRelationConditionFor(containsOrderEntryRaoVariable).withAttribute("product")
					.withOperator(RuleIrAttributeOperator.EQUAL).withTargetVariable(containsProductRaoVariable).build();// 156 158 159 160 161
			final RuleIrAttributeCondition irContainsOrderEntryQuantityCondition = RuleIrAttributeConditionBuilder
					.newAttributeConditionFor(containsOrderEntryRaoVariable).withAttribute("quantity")
					.withOperator(RuleIrAttributeOperator.valueOf(operator.name())).withValue(quantity).build();// 162 164 165 166 167
			final RuleIrAttributeRelCondition irContainsCartOrderEntryRel = RuleIrAttributeRelConditionBuilder
					.newAttributeRelationConditionFor(cartRaoVariable).withAttribute("entries")
					.withOperator(RuleIrAttributeOperator.CONTAINS).withTargetVariable(containsOrderEntryRaoVariable).build();// 168 169 170 171 172
			final RuleIrExistsCondition irExistsProductCondition = new RuleIrExistsCondition();// 173
			irExistsProductCondition.setVariablesContainer(variablesContainer);// 174
			irExistsProductCondition.setChildren(Arrays.asList(new RuleIrCondition[]
			{ irContainsProductCondition, irContainsOrderEntryProductRel, irContainsOrderEntryQuantityCondition,
					irContainsCartOrderEntryRel }));// 175
			irQualifyingProductsCondition.getChildren().add(irExistsProductCondition);// 178
		}

	}

}
