import { Moment } from 'moment';
import { IBook } from 'app/shared/model/book.model';
import { ChapterType } from 'app/shared/model/enumerations/chapter-type.model';
import { StatusFlag } from 'app/shared/model/enumerations/status-flag.model';

export interface IChapter {
  id?: number;
  chapterTitle?: string;
  chapterNumber?: string;
  pages?: number;
  type?: ChapterType;
  order?: number;
  status?: StatusFlag;
  dateAdded?: Moment;
  dateModified?: Moment;
  bookISBN?: IBook;
}

export class Chapter implements IChapter {
  constructor(
    public id?: number,
    public chapterTitle?: string,
    public chapterNumber?: string,
    public pages?: number,
    public type?: ChapterType,
    public order?: number,
    public status?: StatusFlag,
    public dateAdded?: Moment,
    public dateModified?: Moment,
    public bookISBN?: IBook
  ) {}
}
