import { Payment } from './payment';

export class Response {
    status: string;
    message: string;
    access: boolean;
    data: Payment[];
}