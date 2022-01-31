import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Payment } from 'src/app/domain/payments/payment';
import { Request } from 'src/app/domain/payments/request';
import { Response } from 'src/app/domain/payments/response';
import { PaymentsService } from 'src/app/infrastructure/payments/payments.service';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.scss']
})
export class PaymentComponent implements OnInit {

  tittle: string = 'Payments';
  public payment: Request;
  public paymentForm: FormGroup;
  public paymentResponse: Response ;
  public payments: Payment[] = [];
  public requiredFlag: boolean;
  public requirementsFlag: boolean;

  constructor(
    private formBuilder: FormBuilder,
    private paymentService: PaymentsService
  ) { 
    

  }

  ngOnInit(): void {
    this.payment = new Request();

    this.paymentForm = this.formBuilder.group({
      amount: ['', [Validators.required]],
      terms: ['', [Validators.required]],
      rate: ['', [Validators.required]]
    });
  }

  onSubmit() {
    if(this.paymentForm.valid){
      this.requiredFlag = false;
      this.payment.amount = this.paymentForm.value.amount;
      this.payment.rate = this.paymentForm.value.rate / 100;
      this.payment.terms = this.paymentForm.value.terms;

      if(this.validateData(this.payment)){
        this.requirementsFlag = false;

        this.paymentService.payments(this.payment).subscribe(
          response => {
            this.paymentResponse = response as Response;
            this.payments = this.paymentResponse.data;
          },
          error => {
            console.error(error);
          }
  
        );
      } else {
        this.requirementsFlag = true;
      }

    } else {
      this.requiredFlag = true;
    }
  }

  validateData(pay: Request): boolean {
    let bandera: boolean = false;
		// Validating terms
    if (pay.terms >= 4 && pay.terms <= 52) {
      bandera = true;
    } else bandera = false;

		// Validating rate
    if (pay.rate > 0.01 && pay.rate < 1 && bandera) {
      bandera = true;
    } else bandera = false;

		// Validating amount
    if (pay.amount > 1.00 && pay.amount < 999999.00 && bandera) {
      bandera = true;
    } else bandera = false;
		
		return bandera;
  }

  clear(): void {
    console.log("Entra al clear")
    this.payments = [];
    this.paymentForm.reset();
    this.requirementsFlag = false;
    this.requiredFlag = false;
  }

}
