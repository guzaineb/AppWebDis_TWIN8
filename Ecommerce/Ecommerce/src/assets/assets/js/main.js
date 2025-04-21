
//=========== Preloader
window.onload = function () {
  window.setTimeout(fadeout, 500);
}

function fadeout() {
  document.querySelector('.preloader').style.opacity = '0';
  document.querySelector('.preloader').style.display = 'none';
}

// ====== main-menu 1 activation 
let openBtn1 = document.querySelector('.menu-style-1 .open-btn');
let closeBtn1 = document.querySelector('.menu-style-1 .close-btn');
let mainMenuWrapper1 = document.querySelector('.menu-style-1 .main-menu-wrapper');
let mainMenuOverlay1 = document.querySelector('.menu-style-1 .main-menu-overlay');

openBtn1.addEventListener('click', () => {
  mainMenuWrapper1.classList.add('open');
  mainMenuOverlay1.classList.add('open');
})
closeBtn1.addEventListener('click', () => {
  mainMenuWrapper1.classList.remove('open');
  mainMenuOverlay1.classList.remove('open');
})
mainMenuOverlay1.addEventListener('click', () => {
  mainMenuWrapper1.classList.remove('open');
  mainMenuOverlay1.classList.remove('open');
})
    
    
//======== tiny slider for content-card-style-7
tns({
    autoplay: true,
    autoplayButtonOutput: false,
    mouseDrag: true,
    gutter: 0,
    container: ".content-card-style-7",
    center: true,
    nav: true,
    controls: false,
    speed: 400,
    controlsText: [
        '<i class="lni lni-arrow-left-circle"></i>',
        '<i class="lni lni-arrow-right-circle"></i>',
    ],
    responsive: {
        0: {
            items: 1,
        },
    }
});
    
//====== counter up 
var cu = new counterUp({
    start: 0,
    duration: 2000,
    intvalues: true,
    interval: 100,
    append: " ",
});
cu.start();