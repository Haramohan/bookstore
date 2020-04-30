import { Moment } from 'moment';
import { IBook } from 'app/shared/model/book.model';
import { ICategory } from 'app/shared/model/category.model';
import { StatusFlag } from 'app/shared/model/enumerations/status-flag.model';

export interface ISubject {
  id?: number;
  subjectName?: string;
  status?: StatusFlag;
  order?: number;
  dateAdded?: Moment;
  dateModified?: Moment;
  subjectNames?: IBook[];
  subjectCategoryName?: ICategory;
}

export class Subject implements ISubject {
  constructor(
    public id?: number,
    public subjectName?: string,
    public status?: StatusFlag,
    public order?: number,
    public dateAdded?: Moment,
    public dateModified?: Moment,
    public subjectNames?: IBook[],
    public subjectCategoryName?: ICategory
  ) {}
}
