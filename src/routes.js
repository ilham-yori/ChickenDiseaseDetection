const path = require('path');
const boom = require('@hapi/boom');
const { nanoid } = require('nanoid');
const { mainPage, mainPageCSS, predictCSS, predictPage, aboutPage, aboutPageCSS } = require('./handler');

const data = [];
const routes = [
    {
        method: 'GET',
        path: '/',
        handler: mainPage,
        options: {
            files: {
                relativeTo: path.join(__dirname, 'static'),
            }
        }
    },
    {
        method: 'GET',
        path: '/predict',
        handler: predictPage,
        options: {
            files: {
                relativeTo: path.join(__dirname, 'static')
            }
        }
    },
    {
        method: 'GET',
        path: '/about',
        handler: aboutPage,
        options: {
            files: {
                relativeTo: path.join(__dirname, 'static')
            }
        }
    },
    {
        method: 'POST',
        path: '/predict',
        handler: (request, h) => {
            const image = request.payload;
            const id = nanoid(16);
            if(!image){
                const response = h.response({
                    status: 'fail',
                    message: 'There is no image in it'
                });
                response.code(404);
                return response;
            }
            const newImage = { id, image };
            data.push(newImage);
            const success = data.filter((d) => d.id === id).length > 0;
            if(success){
                const response = h.response({
                    status: 'success',
                    image: image,
                    message: 'The image is uploaded successfully',
                });
                response.code(200);
                return response;
            }
            const response = h.response({
                status: 'fail',
                message: 'You failed to appear the image'
            })
            response.code(500);
            return response;
        }
    },
    {
        method: 'GET',
        path: '/imageUploaded',
        handler: (request, h) => {
            return h.view('received_image', {image: "https://images.dog.ceo/breeds/kelpie/n02105412_1398.jpg"});
        }
    },
    {
        method: 'GET',
        path: '/index.css',
        handler: mainPageCSS,
        options: {
            files: {
                relativeTo: path.join(__dirname, 'static'),
            }
        }
    },
    {
        method: 'GET',
        path: '/predict.css',
        handler: predictCSS,
        options: {
            files: {
                relativeTo: path.join(__dirname, 'static')
            }
        }
    },
    {
        method: 'GET',
        path: '/about.css',
        handler: aboutPageCSS,
        options: {
            files: {
                relativeTo: path.join(__dirname, 'static')
            }
        }
    },
]
module.exports = routes;