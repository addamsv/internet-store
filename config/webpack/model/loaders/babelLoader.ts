import remPropPlugin from "../../plugins/babel/RemPropPlugin";

export const babelLoader = (isDev: boolean, isTsx: boolean) => {
  return {
    test: isTsx ? /\.(jsx|tsx)$/ : /\.(js|ts)$/,
    use: {
      loader: "babel-loader",
      options: {
        presets: ["@babel/preset-env"],
        plugins: [
          ["i18next-extract", {
            locales: ["ru", "en"],
            keyAsDefaultValue: true,
            outputPath: "public/locales/{{locale}}/{{ns}}.json"
          }],

          ["@babel/plugin-transform-typescript", { isTsx }],

          "@babel/plugin-transform-runtime",

          isTsx && [remPropPlugin, { props: ["data-testid"] }],

          isDev && require.resolve("react-refresh/babel")
        ].filter(Boolean)
      }
    },
    exclude: /node_modules/,
  };
};
