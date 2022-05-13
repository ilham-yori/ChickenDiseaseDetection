const Hapi = require('@hapi/hapi');
const routes = require('./routes');
const registers = require('./register');
const path = require('path');

const init = async() => {
    const server = Hapi.server({
        port: 3000,
        host: 'localhost',
        routes: {
            cors: {
                origin: ['*'],
            },
        },
    });
    await server.register(registers);
    server.views({
        engines: {
            hbs: require('handlebars')
        },
        path: path.join(__dirname, 'views'),
        layout: 'index'
    })
    server.route(routes);
    await server.start();
    console.log('Server running on %s', server.info.uri);
}
init();