import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter, withDebugTracing } from '@angular/router';
import { provideClientHydration } from '@angular/platform-browser';
import { provideHttpClient, withFetch } from '@angular/common/http';
import { routes } from './app.routes';

// Import Angular Material modules
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatMenuModule } from '@angular/material/menu';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes, withDebugTracing()), // ✅ Fixed enableTracing
    provideClientHydration(),
    provideHttpClient(withFetch()), // ✅ Fix for Fetch API calls
    
    // Add Angular Material modules to providers
    MatButtonModule,
    MatCardModule,
    MatTableModule,
    MatToolbarModule,
    MatIconModule,
    MatSidenavModule,
    MatMenuModule,
    MatInputModule,
    MatFormFieldModule
  ]
};
