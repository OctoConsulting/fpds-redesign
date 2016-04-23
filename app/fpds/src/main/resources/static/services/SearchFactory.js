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

		if(period === 'Week'){
			var prevDate = new Date(today);
			prevDate.setDate(today.getDate() - 7);
			var prevMonth = prevDate.getMonth() + 1;
			var preDate = prevMonth + "/" + prevDate.getDate() + "/" + prevDate.getFullYear();
			
			//console.log('week curr ' + preDate);

			return $http.get("/total_awards?start_date=" + preDate + "&end_date=" + now);
		}
		else if(period === 'Month'){
			var previMonth = new Date();
			var preMonth = previMonth.getMonth() + "/" + previMonth.getDate() + "/" + previMonth.getFullYear();
			//console.log('last month ' + preMonth);
			return $http.get("/total_awards?start_date=" + preMonth + "&end_date=" + now);			
		}
		else if(period === 'Year'){
			var prevYear = new Date(today);
			prevYear.setYear(today.getFullYear() - 1);
			var prMonth = prevYear.getMonth() + 1;
			var preYear =  prMonth + "/" + prevYear.getDate() + "/" + prevYear.getFullYear();

			//console.log('last year ' + preYear);
			return $http.get("/total_awards?start_date=" + preYear + "&end_date=" + now);
		}
		else if(period === '2 Years'){
			var tprevYear = new Date(today);
			tprevYear.setYear(today.getFullYear() - 2);
			var trMonth = tprevYear.getMonth() + 1;
			var tYear = trMonth + "/" + tprevYear.getDate() + "/" + tprevYear.getFullYear();

			//console.log('2 opt ' + tYear);
			return $http.get("/total_awards?start_date=" + tYear + "&end_date=" + now);
		}
		
	};
	
	return factory;
};