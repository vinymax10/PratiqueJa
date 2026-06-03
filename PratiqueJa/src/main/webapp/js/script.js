reloadMathJax();

//window.addEventListener('resize', MJrerender);

window.addEventListener('resize', height);
window.addEventListener('load', height);
window.addEventListener('load', function() {
    var pos = sessionStorage.getItem('scrollPos');
    if (pos !== null) {
        window.scrollTo(0, parseInt(pos));
        sessionStorage.removeItem('scrollPos');
    }
});

//let t = -1;
//let delay = 1000;
//function MJrerender() {
//  if (t >= 0) {
//    // If we are still waiting, then the user is still resizing =>
//    // postpone the action further!
//    window.clearTimeout(t);
//  }
//  t = window.setTimeout(function() {
//    MathJax.Hub.Queue(["Rerender",MathJax.Hub]);
//    t = -1; // Reset the handle
//  }, delay);
//};



//MathJax.Hub.Config({
//        "HTML-CSS": { linebreaks: { automatic: false } },
//        SVG: { linebreaks: { automatic: false } }
// });

function reloadMathJax()
{
	MathJax.Hub.Queue(['Typeset',MathJax.Hub]);// versao 2.7
//	MathJax.typesetPromise()// versao 3...
}

function height() 
{
	var heights=document.getElementsByClassName("latex");
		
	var maxHeight=0;
	for (let i = 0; i < heights.length; i++) 
	{
		if(maxHeight<heights[i].offsetHeight)
			maxHeight=heights[i].offsetHeight;
	}
//  	alert("maxHeight: "+maxHeight);

  	for (let i = 0; i < heights.length; i++) 
	{
		heights[i].style.height=maxHeight+"px";
	}
}

function enableEnterSubmit(formId) {
  // Escapa ':' dos IDs JSF para uso em querySelector
  function escapeId(id) {
    return id.replace(/:/g, '\\:');
  }

  const form = document.getElementById(formId);
  if (!form) return;

  // Remove handlers anteriores (caso a função seja reexecutada após AJAX)
  form.querySelectorAll('.ui-inputgroup input').forEach(inp => {
    inp.removeEventListener('keydown', inp._enterHandler);
  });

  // Para cada input dentro do form...
  form.querySelectorAll('.ui-inputgroup input').forEach(input => {
    const div = input.closest('.ui-inputgroup');
    if (!div) return;

    // Procura o botão dentro do mesmo grupo
    const btn = div.querySelector('button[id*="responder_"]');
    if (!btn) return;

    // Define handler de Enter
    const handler = function (e) {
      if (e.key === 'Enter') {
         e.preventDefault();
		 e.stopPropagation(); // impede o f:ajax interno do inputNumber
		 setTimeout(() => btn.click(), 10); // dá tempo do JSF estabilizar
      }
    };

    input._enterHandler = handler;
    input.addEventListener('keydown', handler);
  });
}


//$(document).ready(function() 
//{
//    $('input:visible:enabled:first').focus();
//    
//    $(document).find('input').keypress(function (e) 
//    {
//        if (e.which == 13) 
//        {//Enter key pressed
//            e.preventDefault();
//           // Obter o próximo índice do elemento de entrada de texto
//            var next_idx = $('input[type=text]').index(this) + 1;
// 
//            // Obter o número de elemento de entrada de texto em um documento html
//            var tot_idx = $('body').find('input[type=text]').length;
//
//            // Entra na tecla no código ASCII
//            if (e.keyCode === 13) 
//            {
//                if (tot_idx == next_idx)
//		 		{
//                    // Vá para o primeiro elemento de texto
////                    $('input:visible:enabled:first').focus();
//						$(".finalizar").focus();
//                    
//				}	
//                else
//                    // Vá para o elemento de entrada de texto seguinte
//                    $('input[type=text]:eq(' + next_idx + ')').focus();
//            }
//        }
//    });
//});

function navegarComScroll(url) {
    sessionStorage.setItem('scrollPos', window.scrollY);
    location.href = url;
}

function asShowTab(tabName, el) {
    document.querySelectorAll('.as-panel-content').forEach(function(p) {
        p.style.display = 'none';
    });
    var panel = document.getElementById('asPanel-' + tabName);
    if (panel) panel.style.display = 'block';
    document.querySelectorAll('#asTabs .as-tab').forEach(function(t) {
        t.classList.remove('as-tab-active');
    });
    if (el) el.classList.add('as-tab-active');
}

function focusFistCampo(idElement) 
{
	console.log(idElement);
	var campo = document.getElementById(idElement).firstChild;
	console.log(campo);
	campo.focus();
//	strLen = campo.value.length;
//	console.log(strLen);
//	campo.setSelectionRange(1, 1);
	
}

// Utilizado nas telas de filtro grande. 
// Para abrir com deslizando dropdown.
function slideToggle(id) 
{
	duration = 300;
	el = document.getElementById(id);
  if (!el) return;

  if (window.getComputedStyle(el).display === "none") {
    // Slide Down
    el.style.display = "block";
    let height = el.scrollHeight;
    el.style.overflow = "hidden";
    el.style.height = "0px";
    el.offsetHeight; // força reflow
    el.style.transition = `height ${duration}ms ease`;
    el.style.height = height + "px";

    setTimeout(() => {
      el.style.height = "";
      el.style.overflow = "";
      el.style.transition = "";
    }, duration);
  } else {
    // Slide Up
    let height = el.scrollHeight;
    el.style.height = height + "px";
    el.style.overflow = "hidden";
    el.offsetHeight;
    el.style.transition = `height ${duration}ms ease`;
    el.style.height = "0px";

    setTimeout(() => {
      el.style.display = "none";
      el.style.height = "";
      el.style.overflow = "";
      el.style.transition = "";
    }, duration);
  }
}

function manterFiltroAberto(id) {
  const filtros = document.getElementById(id);
  if (window.getComputedStyle(filtros).display === "none") {
    filtros.style.display = "block";
  }
}

