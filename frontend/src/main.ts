import { bootstrapApplication } from '@angular/platform-browser';
import { importProvidersFrom } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';

import { routes } from './app/app.routes'; // Import routes
import { AppComponent } from './app/app.component'; // ✅ Bootstrapping AppComponent

bootstrapApplication(AppComponent, {
  providers: [
    importProvidersFrom(HttpClientModule, RouterModule.forRoot(routes)), 
    provideAnimationsAsync()
  ]
})
.catch(err => console.error(err));
