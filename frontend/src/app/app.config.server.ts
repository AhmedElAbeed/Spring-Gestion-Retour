import { mergeApplicationConfig, ApplicationConfig } from '@angular/core';
import { provideServerRendering } from '@angular/platform-server';
import { provideHttpClient } from '@angular/common/http'; // ✅ Add this
import { appConfig } from './app.config';

const serverConfig: ApplicationConfig = {
  providers: [
    provideServerRendering(),
    provideHttpClient() // ✅ Fix for HttpClient error in SSR
  ]
};

export const config = mergeApplicationConfig(appConfig, serverConfig);
