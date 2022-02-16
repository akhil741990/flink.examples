package org.soul.custom.connector.source;

import org.apache.flink.table.connector.ChangelogMode;
import org.apache.flink.table.connector.source.DynamicTableSource;
import org.apache.flink.table.connector.source.ScanTableSource;
import org.apache.flink.table.connector.source.SourceFunctionProvider;

//https://flink.apache.org/2021/09/07/connector-table-sql-api-part1.html
public class MySourceTable implements ScanTableSource {

	@Override
	public String asSummaryString() {
		// TODO Auto-generated method stub
		return "MySourceTable";
	}

	@Override
	public DynamicTableSource copy() {
		// TODO Auto-generated method stub
		return new MySourceTable();
	}

	/*
	 * ChangelogMode informs Flink of expected changes that the planner can expect during runtime.
	 * For example, whether the source produces only new rows,
	 * also updates to existing ones, or whether it can remove previously produced rows(non-Javadoc)
	 * @see org.apache.flink.table.connector.source.ScanTableSource#getChangelogMode()
	 */
	@Override
	public ChangelogMode getChangelogMode() {
		// TODO Auto-generated method stub
		return ChangelogMode.insertOnly();
	}

	/*
	 *  ScanRuntimeProvider allows Flink to create the actual runtime implementation
	 *  you established previously (for reading the data). Flink even provides utilities
	 *  like SourceFunctionProvider to wrap it into an instance of SourceFunction, which is one of the base runtime interfaces(non-Javadoc)
	 * @see org.apache.flink.table.connector.source.ScanTableSource#getScanRuntimeProvider(org.apache.flink.table.connector.source.ScanTableSource.ScanContext)
	 */
	@Override
	public ScanRuntimeProvider getScanRuntimeProvider(ScanContext arg0) {
		// TODO Auto-generated method stub
		boolean isBounded = false;
		final MySource source = new MySource();
		return SourceFunctionProvider.of(source, isBounded);
	}

}
