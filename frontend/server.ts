import { APP_BASE_HREF } from '@angular/common';
import express from 'express';
import { fileURLToPath } from 'node:url';
import { dirname, join, resolve } from 'node:path';
import { ngExpressEngine } from '@nguniversal/express-engine';  // Import ngExpressEngine for SSR
import { bootstrapApplication } from '@angular/platform-browser'; // Your bootstrap method
import { AppComponent } from './src/app/app.component';
import { config } from './src/app/app.config.server';

// The Express app is exported so that it can be used by serverless Functions.
export function app(): express.Express {
  const server = express();
  const serverDistFolder = dirname(fileURLToPath(import.meta.url));
  const browserDistFolder = resolve(serverDistFolder, '../browser');
  const indexHtml = join(serverDistFolder, 'index.server.html');

  // Set up ngExpressEngine for SSR rendering
  server.engine('html', ngExpressEngine({
    bootstrap: () => bootstrapApplication(AppComponent, config),  // Bootstrapping the app for SSR
    providers: [
      { provide: APP_BASE_HREF, useValue: '/' }
    ]
  }));

  // Set view engine to 'html' (because we're rendering Angular views as HTML)
  server.set('view engine', 'html');
  server.set('views', browserDistFolder);

  // Serve static files from /browser
  server.get('**', express.static(browserDistFolder, {
    maxAge: '1y',
    index: 'index.html',
  }));

  // Use ngExpressEngine for all requests to render HTML
  server.get('**', (req, res, next) => {
    const { protocol, originalUrl, headers } = req;

    // Render using the Angular engine (server-side rendering)
    res.render(indexHtml, {
      req,
      providers: [
        { provide: APP_BASE_HREF, useValue: req.baseUrl },
      ],
    }, (err, html) => {
      if (err) {
        return next(err);
      }
      res.send(html);
    });
  });

  return server;
}

// Start the server
function run(): void {
  const port = process.env['PORT'] || 4000;

  // Start up the Node server
  const server = app();
  server.listen(port, () => {
    console.log(`Node Express server listening on http://localhost:${port}`);
  });
}

run();
