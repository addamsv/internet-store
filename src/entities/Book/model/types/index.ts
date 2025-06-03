import { EBlockOfBookType, EBookOfHashTagType } from "../consts";

export interface IBlockOfBook {
  id: string;
  type: EBlockOfBookType
}

export interface IBlockOfBookImage extends IBlockOfBook {
  type: EBlockOfBookType.IMAGE;
  src: string;
  title: string;
}

export interface IBlockOfBookText extends IBlockOfBook {
  type: EBlockOfBookType.TEXT;
  title?: string;
  paragraphs: string[];
}

export interface IBlockOfBookCode extends IBlockOfBook {
  type: EBlockOfBookType.CODE;
  code: string;
}

export type TBookBlock = IBlockOfBookImage | IBlockOfBookText | IBlockOfBookCode;

export interface IBook {
  // title: string;
  // subTitle: string;
  // createdAt: string;
  // hashTagType: EBookOfHashTagType[];

  id: number;
  owner?: number;
  views: number;
  link: string;
  linEx?: string;
  blocks: TBookBlock[];

  img: string;
  Icon?: string;

  Title?: string;
  Series?: string;
  Author?: string[];
  Translator?: string[];
  Narrated?: string[];
  Length?: string,
  "ReleaseDate"?: string;
  "PublicationDate"?: string;
  Language?: string;
  Genres?: EBookOfHashTagType[];
  Format?: "Unabridged Audiobook" | "Podcast" | "Audio Drama";
  Publisher?: string;

  enabled?: boolean;
}

/**
 *
 *          === Book Details State Schema ===
 *
*/
export interface IBookDetailsStateSchema {
  isLoading: boolean;
  error?: string;
  data?: IBook;
}
