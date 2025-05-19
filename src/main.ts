// filepath: /home/k4ts0v/Montederramo/Angular/montedarramo/src/main.ts
import { bootstrapApplication } from '@angular/platform-browser';
import { provideHttpClient } from '@angular/common/http'; // Import this
import { AppComponent } from './app/app.component'; // Or your standalone root component
import { appConfig } from './app/app.config'; // If you have app.config.ts

// If app.config.ts already includes provideHttpClient(), this is simpler:
bootstrapApplication(AppComponent, appConfig)
  .catch((err) => console.error(err));