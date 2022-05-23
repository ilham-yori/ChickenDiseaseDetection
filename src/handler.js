
const mainPage = (request, h) => {
    return h.file("index.html");
}
const mainPageCSS = (request, h) => {
    return h.file('index.css');
}
const predictPage = (request, h) => {
    return h.file('predict_page.html');
}
const predictCSS = (request, h) => {
    return h.file('predict_page.css');
}
const aboutPage = (request, h) => {
    return h.file('About.html');
}
const aboutPageCSS = (request, h) => {
    return h.file('About.css');
}
module.exports = { mainPage, mainPageCSS, predictPage, predictCSS, aboutPage, aboutPageCSS };