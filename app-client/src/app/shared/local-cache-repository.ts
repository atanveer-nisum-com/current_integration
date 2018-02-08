export class LocalCacheRepository<T> {

	private localCache: T[] = [];
	defaultBatchSize: number = 12;
	private capicity: number = 50;

	haveEnoughData: boolean = true;

	set batchSize(dSize: number) {
		this.defaultBatchSize = dSize;
	}

	constructor(private bSize: number = 12) { this.defaultBatchSize = bSize; }

	cacheData(data: T[]) {
		this.localCache = this.localCache.concat(data);
		this.normalizeHaveEnoughDataAttribute();
	}

	clear() {
		this.localCache = [];
	}

	private normalizeHaveEnoughDataAttribute() {
		let isEnoughData = false;
		if (this.localCache == null) {

		} else if (this.localCache.length == 0) {

		} else if (this.localCache.length > 0) {
			isEnoughData = true;
		}

		this.haveEnoughData = isEnoughData;
	}


	next(): T[] {

		let arrayOfDataToReturn = [];
		let isEnoughData = false;

		if (this.haveEnoughData) {

			arrayOfDataToReturn = this.localCache.slice(0, this.defaultBatchSize);

			let nextlocalCacheArrayLength = this.localCache.length - arrayOfDataToReturn.length;

			if (nextlocalCacheArrayLength > 0) {
				isEnoughData = true;
				this.localCache = this.localCache.slice(this.defaultBatchSize, this.localCache.length);
			} else if (nextlocalCacheArrayLength <= 0) {

				this.localCache = [];
				isEnoughData = false;
			}
			this.haveEnoughData = isEnoughData;

			return arrayOfDataToReturn;
		}

		arrayOfDataToReturn = this.localCache;
		this.localCache = [];

		this.haveEnoughData = isEnoughData;

		return arrayOfDataToReturn;
	}

}