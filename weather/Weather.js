
import React from 'react';
import propTypes from 'prop-types';
import { StyleSheet, Text, View } from 'react-native';

export default function Weather({ temp }) {
  return (
    <View style={styles.container}>
      <Text style={styles.text}>{ temp }</Text>
    </View>
  );
}

Weather.propTypes = {
  temp: propTypes.number.isRequired
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#f1f1f1',
    alignItems: 'center',
    justifyContent: 'center',
  },
  text: {
    color: '#2c2c2c',
    fontSize: 30,
  }
});