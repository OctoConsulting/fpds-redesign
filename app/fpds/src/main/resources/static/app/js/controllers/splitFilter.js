module.exports = function(){

	return function(input,splitChar,splitIndex){
		if(typeof input != 'undefined')
			return input.split(splitChar)[splitIndex];
	};
}; 