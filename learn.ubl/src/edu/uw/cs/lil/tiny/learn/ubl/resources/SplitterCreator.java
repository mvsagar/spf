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
package edu.uw.cs.lil.tiny.learn.ubl.resources;

import edu.uw.cs.lil.tiny.ccg.categories.ICategoryServices;
import edu.uw.cs.lil.tiny.explat.IResourceRepository;
import edu.uw.cs.lil.tiny.explat.ParameterizedExperiment;
import edu.uw.cs.lil.tiny.explat.ParameterizedExperiment.Parameters;
import edu.uw.cs.lil.tiny.explat.resources.IResourceObjectCreator;
import edu.uw.cs.lil.tiny.explat.resources.usage.ResourceUsage;
import edu.uw.cs.lil.tiny.learn.ubl.splitting.Splitter;
import edu.uw.cs.lil.tiny.mr.lambda.LogicalExpression;

public class SplitterCreator implements IResourceObjectCreator<Splitter> {
	
	@SuppressWarnings("unchecked")
	@Override
	public Splitter create(Parameters parameters,
			IResourceRepository resourceRepo) {
		return new Splitter(
				(ICategoryServices<LogicalExpression>) resourceRepo
						.getResource(ParameterizedExperiment.CATEGORY_SERVICES_RESOURCE));
	}
	
	@Override
	public String type() {
		return "ubl.splitter";
	}
	
	@Override
	public ResourceUsage usage() {
		return new ResourceUsage.Builder(type(), Splitter.class)
				.setDescription("Logical expression splitter for UBL").build();
	}
	
}