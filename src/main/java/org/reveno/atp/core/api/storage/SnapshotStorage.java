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

package org.reveno.atp.core.api.storage;

import org.reveno.atp.core.api.channel.Channel;

public interface SnapshotStorage {

	Channel сhannel(String address);

	SnapshotStore getLastSnapshotStore();
	
	SnapshotStore nextSnapshotStore();
	
	void removeLastSnapshotStore();
	
	
	public static class SnapshotStore {
		private String snapshotPath;
		public String getSnapshotPath() {
			return snapshotPath;
		}
		
		private long snapshotTime;
		public long getTime() {
			return snapshotTime;
		}
		
		public SnapshotStore(String path, long time) {
			this.snapshotPath = path;
			this.snapshotTime = time;
		}
	}
	
}
