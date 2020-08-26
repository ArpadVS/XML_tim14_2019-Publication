import { Deserializable } from './deserializable.model';

export class RequestResponse implements Deserializable {
    requestId: number;
    confirmed: boolean;

  deserialize(input: any) {
    Object.assign(this, input);
    return this;
  }
}
