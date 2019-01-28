/**
 *
 */
package com.ldr.solrfacetsearch.indexer.cron;

import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.jalo.CronJobProgressTracker;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.solrfacetsearch.indexer.cron.SolrIndexerJob;


/**
 * @author devreddy
 *
 */
public class ApparelSolrIndexerJob extends SolrIndexerJob
{
	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.solrfacetsearch.indexer.cron.AbstractIndexerJob#perform(de.hybris.platform.cronjob.model.
	 * CronJobModel)
	 */
	@Override
	public PerformResult perform(final CronJobModel cronJob)
	{
		super.perform(cronJob);
		final CronJobProgressTracker tracker = new CronJobProgressTracker(modelService.getSource(cronJob)); // <- new tracker instance is created
		for (int i = 1; i < 100; i++)
		{
			try
			{
				tracker.setProgress(Double.valueOf(i)); // <- set progress
				Thread.sleep(Double.valueOf(100 + (1000 * Math.random())).intValue());
			}
			catch (final InterruptedException e)
			{
				return new PerformResult(CronJobResult.FAILURE, CronJobStatus.ABORTED);
			}
		}
		tracker.close(); // <- save last progress to the database
		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}
}
