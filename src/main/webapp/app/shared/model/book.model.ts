import { Moment } from 'moment';
import { IChapter } from 'app/shared/model/chapter.model';
import { ISubject } from 'app/shared/model/subject.model';
import { Lang } from 'app/shared/model/enumerations/lang.model';
import { StatusFlag } from 'app/shared/model/enumerations/status-flag.model';

export interface IBook {
  id?: number;
  booktitle?: string;
  text?: string;
  isbn?: string;
  publisher?: string;
  language?: Lang;
  pubyear?: number;
  pages?: number;
  price?: number;
  author?: string;
  status?: StatusFlag;
  dateAdded?: Moment;
  dateModified?: Moment;
  isbns?: IChapter[];
  subjectName?: ISubject;
}

export class Book implements IBook {
  constructor(
    public id?: number,
    public booktitle?: string,
    public text?: string,
    public isbn?: string,
    public publisher?: string,
    public language?: Lang,
    public pubyear?: number,
    public pages?: number,
    public price?: number,
    public author?: string,
    public status?: StatusFlag,
    public dateAdded?: Moment,
    public dateModified?: Moment,
    public isbns?: IChapter[],
    public subjectName?: ISubject
  ) {}
}
