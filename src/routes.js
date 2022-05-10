const path = require('path');
const boom = require('@hapi/boom');
const { mainPage, mainPageCSS } = require('./handler');

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
        path: '/index.css',
        handler: mainPageCSS,
        options: {
            files: {
                relativeTo: path.join(__dirname, 'static'),
            }
        }
    },
]
module.exports = routes;