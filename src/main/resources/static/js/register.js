//登録ボタン押下時に呼び出されるイベントリスナー
const btn = document.getElementById('btn');

	btn.addEventListener("click", function() {

	 var inputName = document.getElementById("inputName").value;
 	 var inputPassword1 = document.getElementById("inputPassword1").value;
 	 var inputPassword2 = document.getElementById("inputPassword2").value;

 	 if(inputName.match(/[^A-Za-z0-9]+/)){
 	 	alert('半角英数字で入力してください');
 	 	document.getElementById("inputName").style.backgroundColor = 'red';
 	 }
 	 if(inputName=="" || inputName== null){
 	 	alert('空欄があります');
 		 	document.getElementById("inputName").style.backgroundColor = 'red';
  	}
 	 if(inputPassword1=="" || inputPassword1== null ){
 	 	alert('空欄があります');
 		 	document.getElementById("inputPassword1").style.backgroundColor = 'red';
  	}
 	 if(inputPassword2=="" || inputPassword2== null){
 	 	alert('空欄があります');
 		 	document.getElementById("inputPassword2").style.backgroundColor = 'red';
  	}
 	 if(inputPassword1.length < 4 || inputPassword2.length< 4){
 		alert('パスワードは4文字以上で入力してください');
 		document.getElementById("inputPassword1").style.backgroundColor ='red';
 		document.getElementById("inputPassword2").style.backgroundColor = 'red';
 	}
 	 if(inputPassword1.length > 10 || inputPassword2.length > 10){
 	 		alert('パスワードは10文字以内で入力してください');
 	 		document.getElementById("inputPassword1").style.backgroundColor ='red';
 	 		document.getElementById("inputPassword2").style.backgroundColor = 'red';
 	}
 	 if(inputPassword1!=inputPassword2){
		alert('パスワードが一致していません');
		document.getElementById("inputPassword1").style.backgroundColor = 'red';
 		document.getElementById("inputPassword2").style.backgroundColor = 'red';

	}

    });