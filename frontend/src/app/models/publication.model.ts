import { Deserializable } from './deserializable.model';

export class Publication implements Deserializable {
    id: string;
    author: string;
    title: string;
    status: string;

    deserialize(input: any) {
        Object.assign(this, input);
        return this;
      }
}
