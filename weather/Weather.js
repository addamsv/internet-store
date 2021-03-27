
import React from 'react';
import PropTypes from 'prop-types';
import { StyleSheet, Text, View } from 'react-native';
import { Ionicons } from '@expo/vector-icons';

export default function Weather({ temp, condition }) {
  return (
    <View style={styles.container}>
      <Text style={styles.text}>{ temp }</Text>
      <Ionicons name="md-checkmark-circle" size={32} color="green" />
    </View>
  );
}

Weather.propTypes = {
  temp: PropTypes.number.isRequired,
  condition: PropTypes.oneOf(['Thunderstorm', 'Drizzle', 'Rain', 'Snow', 'Clear', 'Atmosphere', 'Clouds']).isRequired
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