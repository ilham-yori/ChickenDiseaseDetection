
const mainPage = (request, h) => {
    return h.file("index.html");
}
const mainPageCSS = (request, h) => {
    return h.file('index.css');
}

module.exports = { mainPage, mainPageCSS };