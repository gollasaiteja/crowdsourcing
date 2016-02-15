
$('.btn-group').button();

var btns = ['btn-one', 'btn-two'];
var input = document.getElementById('btn-input');
for(var i = 0; i < btns.length; i++) {
  document.getElementById(btns[i]).addEventListener('click', function() {
    input.value = this.value;
  });
}