import { Reply } from "./Reply";

export interface Tweet {
    id?: number,
    message?: string,
    postedBy?: string,
    postedAt?: string,
    replies?: Array<Reply>
}