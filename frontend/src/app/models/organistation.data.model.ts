import { Deserializable } from './deserializable.model';

export class OrganisationData implements Deserializable {
    id: number;
    holderName: string;
    organisation: string;
    organisationUnit: string;
    country: string;
    uniqueIdentifier: string;
    holderMail: string;
    certificateBase64: string;
    publicKey: string;
    active: boolean;

  deserialize(input: any) {
    Object.assign(this, input);
    return this;
  }
}
