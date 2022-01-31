import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RouterModule, Routes } from '@angular/router';
import { PaymentComponent } from './ui/payment/payment.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

/*
* Angular material
*/
import { MatSliderModule } from '@angular/material/slider';
import {MatFormFieldModule} from '@angular/material/form-field';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { CurrencyPipe } from './ui/pipes/currency.pipe';

const routes:Routes = [
  {path: '', redirectTo: '/payments', pathMatch: 'full'},
  {path: 'payments', component: PaymentComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    PaymentComponent,
    CurrencyPipe,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule.forRoot(routes),
    BrowserAnimationsModule,
    FormsModule, 
    ReactiveFormsModule,
    HttpClientModule,
  ],
  exports: [
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
