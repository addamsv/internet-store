import webpack from "webpack";
import { IOptions } from "../types";
import { svgLoader } from "./loaders/svgLoader";
import { cssLoader } from "./loaders/cssLoader";
import { fileLoader } from "./loaders/fileLoader";
import { typescriptLoader } from "./loaders/tsLoader";
import { babelLoader } from "./loaders/babelLoader";

export function loaders(options: IOptions): webpack.RuleSetRule[] {
  const { isDev } = options;

  return [
    fileLoader,

    svgLoader(),

    babelLoader(isDev, false), // tsx

    babelLoader(isDev, true), // ts

    // typescriptLoader,

    cssLoader(isDev),
  ];
}
