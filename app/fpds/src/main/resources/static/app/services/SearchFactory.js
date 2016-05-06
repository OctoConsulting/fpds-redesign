module.exports = function($http){

	var factory = {};

	factory.getItems = function(){
		$http.get("/total").success(function(data) {
    		console.log(data);
  		});
	};

	factory.getSearch = function(){
			return $http.get("http://localhost:8080/search?q=" + "octo");
		
	};

	factory.getVendor = function(vendorName){
		//console.log("Vendor " + vendorName);
		return $http.get("/search?q=" + vendorName);
	};

	factory.getContract = function(period){
		console.log(period);
		var today = new Date();
		var currMonth = today.getMonth() + 1;
		var now = currMonth + "/" + today.getDate() + "/" + today.getFullYear();

		//console.log('today ' + now);
		var sdate = getPeriod(period);

		return $http.get("/total_awards?start_date=" + sdate + "&end_date=" + now);
	};

	factory.getPrime = function(period){
		console.log(period);
		var today = new Date();
		var currMonth = today.getMonth() + 1;
		var now = currMonth + "/" + today.getDate() + "/" + today.getFullYear();
		var sdate = getPeriod(period);
		
		return $http.get("/prime_contracts?start_date=" + sdate + "&end_date=" + now);

	};

	factory.getSearchDetails = function(vendor,start,size){
		//console.log("getSearchDetails " + vendor  + " " + start + " " + size);
		return $http.get("/results?q=" + vendor + "&from=" + start + "&size=" + size);
	};

	factory.getViewContracts = function(contractId){
		return $http.get("/piidcontracts?piid=" + contractId);
	};

	factory.getViewIdv = function(idvId){
		return $http.get("/idvcontracts?idv=" + idvId);
	};

	getPeriod = function(period){
		var today = new Date();

		if(period === 'Past Week'){
			var preDate = new Date(today);
			preDate.setDate(today.getDate() - 7);
			var wmonth = preDate.getMonth() + 1;
			var wdate = wmonth + "/" + preDate.getDate() + "/" + preDate.getFullYear();
			return wdate;
		}
		else if(period === 'Past Month'){
			var preMonth = new Date(today);
			var mdate = preMonth.getMonth() + "/" + preMonth.getDate() + "/" + preMonth.getFullYear();
			return mdate;
		}
		else if(period === 'Past Year'){
			var preYear = new Date(today);
			preYear.setYear(today.getFullYear() - 1);
			var ymonth = preYear.getMonth() + 1;
			var ydate = ymonth + "/" + preYear.getDate() + "/" + preYear.getFullYear();
			return ydate;
		}
		else if(period === 'Past 2 years'){
			var preTyear = new Date(today);
			preTyear.setYear(today.getFullYear() - 2);
			var ytmonth = preTyear.getMonth() + 1;
			var ytdate = ytmonth + "/" + preTyear.getDate() + "/" + preTyear.getFullYear();
			return ytdate;
		}

	};
	
	return factory;
};