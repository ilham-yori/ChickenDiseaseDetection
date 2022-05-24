const fs = require('fs');

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
const indexhbsCSS = (request, h) => {
    return h.file('indexhbs.css');
}
const uploadImage = async (request, h) => {
    const { payload } = request;
    const filenames = payload.filename.hapi.filename;
    const data = payload.filename._data;
    return new Promise((resolve, reject) => fs.writeFile('src/Upload/' + filenames, data, err => {
        if(err){
            console.log(err);
            reject(err)
        }
        resolve(h.view('received_image', {image: 'http://localhost:3000/predict/'+filenames}));
    }))
}
module.exports = { mainPage, mainPageCSS, predictPage, predictCSS, aboutPage, aboutPageCSS, indexhbsCSS, uploadImage };