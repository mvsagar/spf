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
package edu.uw.cs.lil.tiny.mr.lambda.visitor;

import edu.uw.cs.lil.tiny.mr.lambda.Lambda;
import edu.uw.cs.lil.tiny.mr.lambda.Literal;
import edu.uw.cs.lil.tiny.mr.lambda.LogicalConstant;
import edu.uw.cs.lil.tiny.mr.lambda.LogicalExpression;
import edu.uw.cs.lil.tiny.mr.lambda.Variable;

/**
 * Visitor to check if a logical expression is an extended constant. A logical
 * expression is an extended constant if:
 * <ul>
 * <li>1. It's a logical constant {@link LogicalConstant}.</li>
 * <li>2. It's a literal with the predicate being an extended constant and each
 * argument being an extended constant.</li>
 * <li>3. Otherwise it's not an extended constant.</li>
 * </ul>
 * 
 * @author Yoav Artzi
 */
public class IsExtendedConstant implements ILogicalExpressionVisitor {
	private boolean	isExtendedConstant	= true;
	
	private IsExtendedConstant() {
		// Usage only through static 'of' method.
	}
	
	public static boolean of(LogicalExpression exp) {
		final IsExtendedConstant visitor = new IsExtendedConstant();
		visitor.visit(exp);
		return visitor.result();
	}
	
	public boolean result() {
		return isExtendedConstant;
	}
	
	@Override
	public void visit(Lambda lambda) {
		isExtendedConstant = false;
	}
	
	@Override
	public void visit(Literal literal) {
		literal.getPredicate().accept(this);
		for (final LogicalExpression arg : literal.getArguments()) {
			arg.accept(this);
		}
	}
	
	@Override
	public void visit(LogicalConstant logicalConstant) {
		// Nothing to do since we start with the 'true' assumption
	}
	
	@Override
	public void visit(LogicalExpression logicalExpression) {
		logicalExpression.accept(this);
	}
	
	@Override
	public void visit(Variable variable) {
		isExtendedConstant = false;
	}
}
