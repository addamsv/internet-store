export interface IFloatFooterItem {
  path: string;

  text: string;

  Icon?: React.VFC<React.SVGProps<SVGSVGElement>>;

  authOnly?: boolean;
}
