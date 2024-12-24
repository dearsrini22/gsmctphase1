export class AssociationSearch {
    text?: string | null | undefined;
    state?: string;

    constructor(text?: string | null, state?: string) {
        this.text = text;
        this.state = state;
    }
}
