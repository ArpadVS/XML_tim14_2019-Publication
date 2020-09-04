import { Deserializable } from './deserializable.model';

export class PaperLetter implements Deserializable {
    paper: string;
    letter: string;

    constructor(paper: string, letter: string){
        this.paper = paper;
        this.letter = letter;
    }
    deserialize(input: any) {
        Object.assign(this, input);
        return this;
      }
}
