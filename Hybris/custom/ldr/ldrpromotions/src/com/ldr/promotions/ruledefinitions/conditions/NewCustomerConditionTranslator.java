package com.ldr.promotions.ruledefinitions.conditions;

import de.hybris.platform.ruleengineservices.compiler.RuleCompilerContext;
import de.hybris.platform.ruleengineservices.compiler.RuleCompilerException;
import de.hybris.platform.ruleengineservices.compiler.RuleConditionTranslator;
import de.hybris.platform.ruleengineservices.compiler.RuleIrAttributeCondition;
import de.hybris.platform.ruleengineservices.compiler.RuleIrAttributeOperator;
import de.hybris.platform.ruleengineservices.compiler.RuleIrCondition;
import de.hybris.platform.ruleengineservices.compiler.RuleIrGroupCondition;
import de.hybris.platform.ruleengineservices.compiler.RuleIrGroupOperator;
import de.hybris.platform.ruleengineservices.rao.CartRAO;
import de.hybris.platform.ruleengineservices.rule.data.RuleConditionData;
import de.hybris.platform.ruleengineservices.rule.data.RuleConditionDefinitionData;

import java.util.ArrayList;


/**
 * @author dredd5
 */
public class NewCustomerConditionTranslator implements RuleConditionTranslator
{

	@Override
	public RuleIrCondition translate(final RuleCompilerContext context, final RuleConditionData condition,
			final RuleConditionDefinitionData conditionDefinition) throws RuleCompilerException
	{
		final String cartRaoVariable = context.generateVariable(CartRAO.class);

		final RuleIrGroupCondition newCustomerCondition = new RuleIrGroupCondition();
		newCustomerCondition.setOperator(RuleIrGroupOperator.AND);
		newCustomerCondition.setChildren(new ArrayList());

		final RuleIrAttributeCondition irCartEntry = new RuleIrAttributeCondition();
		irCartEntry.setVariable(cartRaoVariable);
		irCartEntry.setAttribute("newCustomer");
		irCartEntry.setOperator(RuleIrAttributeOperator.EQUAL);
		irCartEntry.setValue(Boolean.TRUE);

		newCustomerCondition.getChildren().add(irCartEntry);

		return newCustomerCondition;
	}

}