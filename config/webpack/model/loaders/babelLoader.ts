export const babelLoader = (isDev: boolean, isTsx: boolean) => {
  return {
    test: isTsx ? /\.(jsx|tsx)$/ : /\.(js|ts)$/,
    use: {
      loader: "babel-loader",
      options: {
        presets: ["@babel/preset-env"],
        plugins: [
          ["i18next-extract", { locales: ["ru", "en"], keyAsDefaultValue: true }],

          ["@babel/plugin-transform-typescript", { isTsx }],

          "@babel/plugin-transform-runtime",

          // isTsx && [babelRemovePropsPlugin, { props: ["data-testid"] }],

          isDev && require.resolve("react-refresh/babel")
        ].filter(Boolean)
      }
    },
    exclude: /node_modules/,
  };
};
