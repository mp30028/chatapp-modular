var data = [
	{ "value": 1, "label": "one" },
	{ "value": 2, "label": "two" },
	{ "value": 3, "label": "three"},
	{ "value": 4, "label": "four" },
	{ "value": 5, "label": "five" }
];

const arrayForEachExample = () => {
	console.log("------------ From arrayForEachExample ------------") 	
	data.forEach((item) => { console.log(item) });	
};

const filterExample = () => {
	var isThreeOrFour = (item) => (item.label === "three" || item.label === "four");
	var filteredData = data.filter(isThreeOrFour)
	console.log("------------ From filterExample ------------") 	
	filteredData.forEach((item) => { console.log(item) });	
};

const findExample = () =>{
	var isGreaterThanTwo = (item) => (item.value > 2);
	var foundData = data.find(isGreaterThanTwo);
	console.log("------------ From findExample ------------") 	
	console.log("The first item with value greater than two is ", foundData);
}

const someExample = () =>{
	var itemFive = { "value": 5, "label": "five" };
	var itemSix = { "value": 6, "label": "six" };
	var containsFive = (item) => (item.value === itemFive.value);
	var containsSix = (item) => (item.value === itemSix.value);
	console.log("------------ From someExample ------------")
 	console.log("Data includes itemFive = ", data.some(containsFive)) 
 	console.log("Data includes itemSix = ", data.some(containsSix))
}

const mapExample = () =>{
	var itemZeroFour = { "value": 4, "label": "zeroFour" };
	replaceFour = (item) => (item.value === 4) ? itemZeroFour : item;
	var mappedData = data.map(replaceFour);
	console.log("------------ From mapExample ------------")
 	mappedData.forEach((item) => { console.log(item) }); 
	
}

const ifPresentThenReplaceElseAdd = (dataToUpdate, checkItem) => {
	var isReplaced = false;
//	var containsCheckItem = (item) => (item.value === checkItem.value);
	var replaceCheckItem = (item) =>  {	
										if (item.value === checkItem.value){
											isReplaced = true;
											return checkItem;
									  	}else{
											return item;	
										}
									  };
	var upddatedData = dataToUpdate.map(replaceCheckItem);
	if (!isReplaced){
		upddatedData.push(checkItem);	
	}
	return upddatedData;
		
}

const ifPresentThenDelete = (dataToUpdate, checkItem) => {
	var notMatchingCheckItem = (item) => (item.value != checkItem.value)
	var updatedData = dataToUpdate.filter(notMatchingCheckItem);
	return updatedData;
}

const replaceOrAddExample = () => {
	var itemZeroFour = { "value": 4, "label": "zeroFour" };
	var itemSix = { "value": 6, "label": "six" };
	var updatedData = ifPresentThenReplaceElseAdd(data, itemZeroFour);
	updatedData = ifPresentThenReplaceElseAdd(updatedData, itemSix);
		console.log("------------ From replaceOrAddExample ------------")
 		updatedData.forEach((item) => { console.log(item) }); 
}

const deleteExample = () =>{
	var itemFour = { "value": 4, "label": "four" };
	var itemSix = { "value": 6, "label": "six" };
	var updatedData = ifPresentThenDelete(data, itemFour);
	updatedData = ifPresentThenDelete(updatedData, itemSix);
		console.log("------------ From deleteExample ------------")
 		updatedData.forEach((item) => { console.log(item) }); 
}

arrayForEachExample();
filterExample();
findExample();
someExample();
mapExample();
replaceOrAddExample();
deleteExample();