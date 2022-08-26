//ログインボタン押下時に呼び出されるイベントリスナー
const btn = document.getElementById('btn');

	btn.addEventListener("click", function() {

	 let inputName = document.getElementById("inputName").value;
 	 let inputPassword = document.getElementById("inputPassword").value;

 	 if(inputName.match(/[^A-Za-z0-9]+/)){
 	 	alert('半角英数字で入力してください');
 	 	document.getElementById("inputName").style.backgroundColor = 'red';
 	 }
 	 if(inputName=="" || inputName== null){
 	 	alert('空欄があります');
 		 	document.getElementById("inputName").style.backgroundColor = 'red';
  	}
 	 if(inputPassword=="" || inputPassword== null ){
 	 	alert('空欄があります');
 		 	document.getElementById("inputPassword").style.backgroundColor = 'red';
  	}

    });