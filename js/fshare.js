
window.fbAsyncInit = function() {
    FB.init({
      appId      : '1185171028168171',
      xfbml      : true,
      version    : 'v2.6'
    });
  };

  (function(d, s, id){
     var js, fjs = d.getElementsByTagName(s)[0];
     if (d.getElementById(id)) {return;}
     js = d.createElement(s); js.id = id;
     js.src = "//connect.facebook.net/en_US/sdk.js";
     fjs.parentNode.insertBefore(js, fjs);
   }(document, 'script', 'facebook-jssdk'));
  
  function postShare(value){
	  var url = 'http://ass3-nravichan.rhcloud.com/Raghavan_Assignment/StudyController?action=sharedStudy&studyCode='.concat(value);
	  console.log(url);
	  FB.ui({method: 'share', href: url, quote: 'NBAD Assignment',
		}, function(response){});
  }