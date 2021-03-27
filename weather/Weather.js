
import React from 'react';
import PropTypes from 'prop-types';
import { StyleSheet, Text, View, StatusBar } from 'react-native';
import { Ionicons } from '@expo/vector-icons';
import { LinearGradient } from 'expo-linear-gradient';

export default function Weather({ temp, condition }) {
  return (
    <LinearGradient colors={['#4c669f', '#3b5998', '#192f6a']} style={styles.container}>
      <StatusBar barStyle='light-content' />
      <View style={styles.item}>
        <Ionicons name="rainy" size={96} color='white' />
        <Text style={styles.text}>{ temp }°C</Text>
      </View>
      <View style={{...styles.item, ...styles.downTown}}>
        <Text style={styles.text}>Rainy</Text>
        <Text style={styles.subText}>Minsk, Belarus</Text>
      </View>
    </LinearGradient>
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
  item: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  downTown: {
    paddingHorizontal: 20,
  },
  text: {
    color: 'white',
    fontSize: 44,
    fontWeight: '300',
    textAlign: 'center',
  },
  subText: {
    color: 'white',
    fontSize: 24,
    textAlign: 'center',
  }
});