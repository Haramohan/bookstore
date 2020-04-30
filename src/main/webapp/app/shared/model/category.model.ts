import { Moment } from 'moment';
import { ISubject } from 'app/shared/model/subject.model';
import { StatusFlag } from 'app/shared/model/enumerations/status-flag.model';

export interface ICategory {
  id?: number;
  categoryName?: string;
  status?: StatusFlag;
  dateAdded?: Moment;
  dateModified?: Moment;
  categoryNames?: ISubject[];
}

export class Category implements ICategory {
  constructor(
    public id?: number,
    public categoryName?: string,
    public status?: StatusFlag,
    public dateAdded?: Moment,
    public dateModified?: Moment,
    public categoryNames?: ISubject[]
  ) {}
}
