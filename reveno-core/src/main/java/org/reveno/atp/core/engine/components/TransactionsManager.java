/** 
 *  Copyright (c) 2015 The original author or authors
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0

 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.reveno.atp.core.engine.components;

import org.reveno.atp.api.transaction.TransactionContext;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class TransactionsManager {

	public <T> void registerTransaction(Class<T> transactionType, BiConsumer<T, TransactionContext> handler) {
		registerTransaction(transactionType, handler, false);
	}
	
	@SuppressWarnings("unchecked")
	public <T> void registerTransaction(Class<T> transactionType, BiConsumer<T, TransactionContext> handler, boolean isCompensate) {
		if (!isCompensate)
			txs.put(transactionType, (BiConsumer<Object, TransactionContext>) handler);
		else 
			compensateTxs.put(transactionType, (BiConsumer<Object, TransactionContext>) handler);
	}
	
	public void execute(Object transaction, TransactionContext context) {
		txs.get(transaction.getClass()).accept(transaction, context);
	}
	
	public void compensate(Object transaction, TransactionContext context) {
		BiConsumer<Object, TransactionContext> compensate = compensateTxs.get(transaction.getClass());
		if (compensate != null)
			compensate.accept(transaction, context);
	}
	
	protected Map<Class<?>, BiConsumer<Object, TransactionContext>> txs = new HashMap<>();
	protected Map<Class<?>, BiConsumer<Object, TransactionContext>> compensateTxs = new HashMap<>();
	
}
