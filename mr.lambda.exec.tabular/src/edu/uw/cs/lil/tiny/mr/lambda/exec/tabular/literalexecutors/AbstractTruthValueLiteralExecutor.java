/*******************************************************************************
 * UW SPF - The University of Washington Semantic Parsing Framework. Copyright (C) 2013 Yoav Artzi
 * <p>
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or any later version.
 * <p>
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * <p>
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 ******************************************************************************/
package edu.uw.cs.lil.tiny.mr.lambda.exec.tabular.literalexecutors;

import java.util.List;
import java.util.Map;

import edu.uw.cs.lil.tiny.mr.lambda.Literal;
import edu.uw.cs.lil.tiny.mr.lambda.LogicalExpression;
import edu.uw.cs.lil.tiny.mr.lambda.exec.tabular.Table;
import edu.uw.cs.utils.collections.ListUtils;

public abstract class AbstractTruthValueLiteralExecutor implements
		ILiteralExecutor {
	
	@Override
	public void execute(Literal literal, Table table) {
		for (final Map<LogicalExpression, Object> row : table) {
			final List<LogicalExpression> args = literal.getArguments();
			final List<Object> objects = ListUtils.map(args,
					new ListUtils.Mapper<LogicalExpression, Object>() {
						
						@Override
						public Object process(LogicalExpression obj) {
							return row.get(obj);
						}
					});
			final boolean rowResult = doExecute(objects);
			row.put(literal, rowResult);
		}
	}
	
	protected abstract boolean doExecute(List<Object> objects);
	
}