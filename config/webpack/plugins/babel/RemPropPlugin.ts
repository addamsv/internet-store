import { PluginItem } from "@babel/core";

/**
 * Используется для удаления "data-testid" из конечной сборки
 *
 * Babel plugin development
 * @link https://babeljs.io/docs/plugins#plugin-development
 *
 * AST explorer:
 * @link https://astexplorer.net/
 */

export default (): PluginItem => {
  return {
    visitor: {
      Program(path, state) {
        const stateOptsProps = state.opts.props || [];

        path.traverse({
          JSXIdentifier(path) {
            if (stateOptsProps.includes(path.node.name)) { // name === "data-testid"
              path.parentPath.remove();
            }
          },
        });
      },
    },
  };
};
