$('.message a').click(function(){
   $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
});


let inputElt = document.getElementById('login_field');
let btn = document.getElementById('login_button');


if (inputElt.value === ''){
  btn.disabled = true;
  btn.style.backgroundColor="#757575";
}else{
  btn.disabled = false;
  btn.style.backgroundColor="#4caf50";
}

inputElt.addEventListener("input", function(){
  if (this.value === ''){
    btn.disabled = true;
    btn.style.backgroundColor="#757575";
  }else{
    btn.disabled = false;
    btn.style.backgroundColor="#4caf50";
  }
})


