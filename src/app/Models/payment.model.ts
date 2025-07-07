export interface PaymentDto {
  idPayment: number;
  fullName: string;
  username: string;
  email: string;
  dni: string;
  reference: string;
  typeShipment: string;
  totalAmount: number;
  amount: number;
  voucherFile: string;
  paymentMethod: string;
  status: string;
  datePayment: string;
}
