/**
 *
 */
package com.ldr.core.search.solrfacetsearch.provider;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.indexer.IndexerBatchContext;
import de.hybris.platform.solrfacetsearch.indexer.spi.InputDocument;
import de.hybris.platform.solrfacetsearch.provider.ValueResolver;
import de.hybris.platform.variants.model.VariantProductModel;

import java.util.Collection;

import com.ldr.core.model.ApparelProductModel;


/**
 * @author devreddy
 *
 */
public class BrandCodeValueResolver implements ValueResolver<ProductModel>
{

	/*
	 *
	 * @see
	 * de.hybris.platform.solrfacetsearch.provider.ValueResolver#resolve(de.hybris.platform.solrfacetsearch.indexer.spi.
	 * InputDocument, de.hybris.platform.solrfacetsearch.indexer.IndexerBatchContext, java.util.Collection,
	 * de.hybris.platform.core.model.ItemModel)
	 */
	@Override
	public void resolve(final InputDocument inputDocument, final IndexerBatchContext indexerBatchContext,
			final Collection<IndexedProperty> indexedProperty, final ProductModel productModel) throws FieldValueProviderException
	{
		inputDocument.addField(indexedProperty.iterator().next(), getBrandFromBaseProduct(productModel));

	}

	/**
	 * @param productModel
	 * @return
	 */
	private String getBrandFromBaseProduct(final ProductModel productModel)
	{

		if (productModel instanceof VariantProductModel)
		{
			final ProductModel productModel1 = ((VariantProductModel) productModel).getBaseProduct();
			if (productModel1 instanceof ApparelProductModel && null != ((ApparelProductModel) productModel1).getBrand())
			{
				return ((ApparelProductModel) productModel1).getBrand().getCode();
			}
		}
		else if (null != ((ApparelProductModel) productModel).getBrand())
		{
			return ((ApparelProductModel) productModel).getBrand().getCode();
		}
		return null;
	}
}
