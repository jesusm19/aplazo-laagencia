import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'currency'
})
export class CurrencyPipe implements PipeTransform {

  transform(value: any, args?: number): any {
    var sign = parseFloat(value);
    return "$ " + sign.toFixed(args);
  }

}
